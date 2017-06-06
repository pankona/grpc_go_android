package hello

import (
	"encoding/json"
	"fmt"
	"net"
	"strconv"

	"golang.org/x/net/context"

	"github.com/pankona/grpc_go_android/app/src/main/go/stringservice"

	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

const (
	// DefaultGRPCServerPort is port number of gRPC server
	DefaultGRPCServerPort = "50053"
)

var largeStringBinding string
var largeStringGRPC *stringservice.LargeString

// Greetings returns greeting to speicifed name
func Greetings(name string) string {

	largeString := &LargeString{
		Stringarray: stringarray,
	}
	out, _ := json.Marshal(largeString)
	largeStringBinding = string(out)

	largeStringGRPC = &stringservice.LargeString{
		StringList: stringarray,
	}

	return fmt.Sprintf("hello %s!", name)
}

// LargeString is ...
type LargeString struct {
	Stringarray []string `json:"stringarray"`
}

// GetLargeString is ...
func GetLargeString() string {
	return largeStringBinding
}

type gRPCServer struct {
}

func (server *gRPCServer) GetLargeString(context.Context, *stringservice.Empty) (*stringservice.LargeString, error) {
	return largeStringGRPC, nil
}

var (
	server *grpc.Server
	port   int
)

// InitializeStringService initializes gRPC Server
func InitializeStringService() int {
	listen, err := net.Listen("tcp", ":"+DefaultGRPCServerPort)
	if err != nil {
		// TODO: error handling
		// increase port number and bind again
	}
	if server != nil {
		// already initialized
		return port
	}
	server = grpc.NewServer()
	stringservice.RegisterStringServiceServer(server, &gRPCServer{})
	reflection.Register(server)

	// this will block
	go server.Serve(listen)

	port, err := strconv.Atoi(DefaultGRPCServerPort)
	if err != nil {
		// TODO: error handling
	}
	return port
}

// FinalizeStringService finalizes gRPC Server
func FinalizeStringService() {
	if server != nil {
		server.GracefulStop()
	}
}
