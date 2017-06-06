# Makefile

STRINGSERVICE_PROTO_SRC=$(CURDIR)/stringservice/stringservice.pb.go
PROTO_DIR=../proto

# lock revision of protobuf. use HEAD if need update.
PROTOBUF_REVISION=a4e8f93323fcf2bc1ae5c036dc4d973872057936

all: gen_proto
	make -C ./hello

clean:
	rm -f $(STRINGSERVICE_PROTO_SRC)

gen_proto: protoc-gen-go $(STRINGSERVICE_PROTO_SRC)

$(STRINGSERVICE_PROTO_SRC): $(PROTO_DIR)/stringservice.proto
	mkdir -p $(dir $@)
	protoc -I $(PROTO_DIR) --go_out=plugins=grpc:$(dir $@) $<

protoc-gen-go:
ifeq ($(shell command -v protoc-gen-go 2> /dev/null),)
	go get -d github.com/golang/protobuf/proto
	cd $(GOPATH)/src/github.com/golang/protobuf && \
	git checkout $(PROTOBUF_REVISION) && \
	go install github.com/golang/protobuf/proto && \
	go install github.com/golang/protobuf/protoc-gen-go
endif
